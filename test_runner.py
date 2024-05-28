import subprocess
import sys
import os
from colorama import init as colorama_init
from colorama import Fore
from colorama import Style
import glob

colorama_init(autoreset=True)
LOGS_DIR = "logs"
MVN_LOGFILE_PATH = os.path.join(LOGS_DIR, "mvn.logs")
test_files = glob.glob("alan_tests/test_files/*")


def log_info(msg):
    print(f"[{Fore.BLUE}{Style.BRIGHT}INFO{Style.RESET_ALL}]: ", msg)


def log_error(msg):
    print(f"[{Fore.RED}{Style.BRIGHT}ERROR{Style.RESET_ALL}]: ", msg)


def get_filename_without_extension(path):
    return os.path.splitext(os.path.basename(path))[0]


def get_key_by_val(dictionary, val):
    return list(dictionary.keys())[list(dictionary.values()).index(val)]


def run_build(output_file=None):
    log_info(f"Starting Maven Build")
    CMD: str = "mvn clean package"
    subprocess.run(CMD.split(), stdout=output_file, stderr=output_file)


def prerun_tests():
    log_info(f"Running pre_test")

    for test in test_files:
        CMD: str = f"java -jar target/compiler-0.0.2.jar"
        stdin = test
        stdout = os.path.join("alan_tests", "correct_output", f"{get_filename_without_extension(test)}.out")
        _cmd_str = f"{CMD} < {test} > {stdout}"
        log_info(f"Running command: `{_cmd_str}`")
        with open(stdin, "r") as stdin_handle, open(stdout, "w") as stdout_handle:
            subprocess.run(CMD.split(), stdin=stdin_handle, stdout=stdout_handle)
            log_info(f"Finished command: `{_cmd_str}`")

    log_info(f"Finished pre_test")


def run_tests():
    log_info(f"--- {Fore.BLUE}{Style.BRIGHT}Running tests{Style.RESET_ALL} ---")

    successful_tests = 0
    failing_tests = 0
    correct_out_files = glob.glob("alan_tests/correct_output/*")
    stderr = os.path.join(LOGS_DIR, f"_temp_err.log")
    test_files_map = {get_filename_without_extension(test): test for test in test_files}
    correct_out_files_map = {get_filename_without_extension(out_file): out_file for out_file in correct_out_files}
    for test in test_files:
        test_title = get_key_by_val(test_files_map, test)
        corresponding_out_file_path = correct_out_files_map.get(test_title)
        log_info(f"--- {Fore.BLUE}{Style.BRIGHT}Checking test{Style.RESET_ALL}: `{test_title}` ---")

        CMD: str = f"java -jar target/compiler-0.0.2.jar"
        stdin = test
        stdout = os.path.join(LOGS_DIR, f"_temp_{test_title}.out")
        _cmd_str = f"{CMD} < {test} > {stdout}"
        log_info(f"Running command: `{_cmd_str}`")
        with open(stdin, "r") as stdin_handle, open(stdout, "w") as stdout_handle, open(stderr, "w") as stderr_handle:
            subprocess.run(CMD.split(), stdin=stdin_handle, stdout=stdout_handle, stderr=stderr_handle)
            log_info(f"Finished command: `{_cmd_str}`")

        with open(stderr, "r") as err_log_handle:
            err_log_content = err_log_handle.read().rstrip()
            if len(err_log_content) != 0:
                log_error(f"{Fore.RED}{Style.BRIGHT}PARSE ERROR{Style.RESET_ALL}: {err_log_content}")
                failing_tests += 1
                continue

        tmp_out_path = stdout
        with open(tmp_out_path, "r") as test_handle, open(corresponding_out_file_path, "r") as correct_handle:
            test_output_content = test_handle.read().rstrip()
            correct_output_content = correct_handle.read().rstrip()

            if test_output_content.startswith("Program("):
                if test_output_content == correct_output_content:
                    log_info(f"{Fore.GREEN}{Style.BRIGHT}Files match")
                    successful_tests += 1
                    os.remove(tmp_out_path)
                else:
                    failing_tests += 1
                    log_error(f"Files don't match")
                    log_error(f"Correct output content: `{correct_output_content}`")
                    log_error(f"Test output content: `{test_output_content}`")
            else:
                failing_tests += 1
                log_error(f"{Fore.RED}{Style.BRIGHT}Couldn't parse file{Style.RESET_ALL}: `{test}`")

    os.remove(stderr)
    log_info(f"--- {Fore.BLUE}{Style.BRIGHT}Finished tests{Style.RESET_ALL} ---")
    return successful_tests, failing_tests


if __name__ == "__main__":
    BUILD_TEST = False
    if "-B" in sys.argv:
        BUILD_TEST = True

    if not os.path.exists(LOGS_DIR):
        os.makedirs(LOGS_DIR)

    with open(MVN_LOGFILE_PATH, "w") as mvn_logfile:
        run_build(mvn_logfile)

    with open(MVN_LOGFILE_PATH, "r") as mvn_logfile:
        lines = mvn_logfile.readlines()
        if "BUILD SUCCESS" in lines[-5]:
            log_info(f"{Fore.GREEN}{Style.BRIGHT}BUILD SUCCESS")
        else:
            log_error(f"There was a problem with the Maven Build.")
            log_info(f"Exiting!")
            exit(1)

    if BUILD_TEST:
        prerun_tests()
    else:
        tests_passed, tests_failed = run_tests()
        log_info(f"Test Passed: {Fore.GREEN}{Style.BRIGHT}{tests_passed}")
        log_info(f"Test Failed: {Fore.RED}{Style.BRIGHT}{tests_failed}")
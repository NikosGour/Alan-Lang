import subprocess
import os
from colorama import init as colorama_init
from colorama import Fore
from colorama import Style
import glob

colorama_init(autoreset=True)


def log_info(msg):
    print(f"[{Fore.BLUE}{Style.BRIGHT}INFO{Style.RESET_ALL}]: ", msg)


def log_error(msg):
    print(f"[{Fore.RED}{Style.BRIGHT}ERROR{Style.RESET_ALL}]: ", msg)


def get_filename_without_extension(path):
    return os.path.splitext(os.path.basename(path))[0]


def run_build(output_file=None):
    log_info(f"Starting Maven Build")
    CMD: str = "mvn clean package"
    subprocess.run(CMD.split(), stdout=output_file, stderr=output_file)


def prerun_tests():
    log_info(f"Running pre_test")
    test_files = glob.glob("alan_tests/test_files/*")

    for test in test_files:
        CMD: str = f"java -jar target/compiler-0.0.2.jar"
        stdin = test
        stdout = os.path.join("alan_tests", "correct_output", f"{get_filename_without_extension(test)}.out")
        _cmd_str = f"{CMD} < {test} > {stdout}"
        log_info(f"Running command: `{_cmd_str}`")
        with open(stdin, "r") as stdin_handle, open(stdout, "w") as stdout_handle:
            subprocess.run(CMD.split(), stdin=stdin_handle, stdout=stdout_handle)
            log_info(f"Finished command: `{_cmd_str}`")


def run_tests():
    test_files = glob.glob("alan_tests/test_files/*")
    correct_out_files = glob.glob("alan_tests/correct_output/*")
    for test in test_files:
        log_info(f"Found test file: `{get_filename_without_extension(test)}`")

    for correct_out in correct_out_files:
        log_info(f"Found correct output file: `{get_filename_without_extension(correct_out)}`")


LOGS_DIR = "logs"
MVN_LOGFILE_PATH = os.path.join(LOGS_DIR, "mvn.logs")

if __name__ == "__main__":
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

    prerun_tests()
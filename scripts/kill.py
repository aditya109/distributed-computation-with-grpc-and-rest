import os
import sys
import signal
import subprocess

port = sys.argv[1]
command = "netstat -ano | findstr " + port
c = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr = subprocess.PIPE)
stdout, stderr = c.communicate()
pid = int(stdout.decode().strip().split(' ')[-1])
os.kill(pid, signal.SIGTERM)
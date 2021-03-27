import os
import sys

port = sys.argv[1]


os.chdir("..")
os.chdir('grpc-server')
print(os.getcwd())
os.system('mvn exec:java -Dexec.args="PORT:' + port + ';"')

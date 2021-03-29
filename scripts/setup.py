import os

os.chdir("..")
os.chdir('grpc-server')
print(os.getcwd())
os.system('mvn clean generate-sources compile install')

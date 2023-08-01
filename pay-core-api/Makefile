.PHONY : all output clean help

uname_S := $(shell sh -c 'uname -s 2>/dev/null || echo not')

application_name := $(shell cat pom.xml | grep finalName | awk -F'[<>]' '{print $$3}')

ifeq (${uname_S}, Darwin)
	SED_CMD=sed -i ''
else
	SED_CMD=sed -i
endif

clean : 
	@(rm -rf output)
	@(rm ${application_name}.tar.gz)
	@(mvn clean)

createfolder :
	@(mkdir -p output/bin output/lib output/conf output/var/log output/var/run)

compile : 
	@(mvn package install -Dmaven.test.skip=true)

copy :
	@(cp -R src/main/resources/* output/conf/)
	@(cp target/*.jar output/lib/)
	@(cp src/main/resources/${application_name}.sh output/bin/${application_name} && chmod +x output/bin/${application_name})

archive :
	@(tar czvf ${application_name}.tar.gz output)

active_profile : 
	@($(SED_CMD) '/^environment=/s/=.*/=${ACTIVE_PROFILE}/' output/conf/${application_name}.conf)
	@(echo "---------------------------------------------------------------")
	@(echo "current active environment is : " && cat output/conf/${application_name}.conf | grep 'environment=')
	@(echo "---------------------------------------------------------------")

all : createfolder compile copy archive

testing : all
	make active_profile ACTIVE_PROFILE=testing

development : all
	make active_profile ACTIVE_PROFILE=development

production : all
	make active_profile ACTIVE_PROFILE=production




help :
	@echo 'Usage: make [TARGET]'
	@echo 'TARGETS:'
	@echo '  all       (=make) compile and link.'
	@echo '  clean     clean objects and the executable file.'
	@echo '  help      print this message.'
	@echo
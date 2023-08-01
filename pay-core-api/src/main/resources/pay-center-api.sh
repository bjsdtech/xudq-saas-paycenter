#!/bin/bash
##! @description: Config for Spring Boot's builtin launch script
##! @version: 1
##! @author: Menglong TAN <tanmenglong@renrenche.com>
##! @date:

filename=`basename "$0"`
prefix="${filename%.*}"

workspace=$(cd $(dirname $0) && pwd)/../

lib_dir=$workspace/lib
conf_dir=$workspace/conf
app_name=$prefix.jar

CONF_FOLDER=$conf_dir $lib_dir/$app_name $@
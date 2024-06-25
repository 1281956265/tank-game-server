set WORKSPACE=.
set LUBAN_DLL=%WORKSPACE%\Luban\Luban.dll
set CONF_ROOT=../src/main/resources/DataTables
set OUT_PUT=../src/main

dotnet %LUBAN_DLL% ^
    -t server ^
    -c java-json ^
    -d json ^
    --conf %CONF_ROOT%\luban.conf ^
    -x outputDataDir=%OUT_PUT%/resources/json ^
    -x outputCodeDir=%OUT_PUT%/gen/cfg

pause
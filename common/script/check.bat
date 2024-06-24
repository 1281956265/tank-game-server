@REM set WORKSPACE=.
@REM
@REM set LUBAN_DLL=%WORKSPACE%\Tools\Luban\Luban.dll
@REM set CONF_ROOT=.\DataTables
@REM
@REM dotnet %LUBAN_DLL% ^
@REM     -t all ^
@REM 	-f ^
@REM     --conf %CONF_ROOT%\luban.conf ^
@REM     -x pathValidator.rootDir=%WORKSPACE%\Projects\Csharp_Unity_bin ^
@REM     -x l10n.textProviderFile=*@%CONF_ROOT%\Datas\l10n\texts.json
@REM
@REM pause
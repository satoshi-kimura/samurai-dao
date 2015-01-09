@echo off
setlocal

set VERSION=1.1.32
set REVISION=747

set REPO=https://samurai-dao.googlecode.com/svn
set MODULE=samurai-dao

REM copy to tags
svn copy -r %REVISION% %REPO%/trunk/%MODULE% %REPO%/tags/%MODULE%-%VERSION% -m "release: %MODULE%-%VERSION%"

endlocal

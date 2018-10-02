@echo off

ECHO Running test cases...

FOR %%x in (*.dat) DO java Driver %%x %%x


ECHO Done!

pause
@echo off

ECHO Running test cases...

FOR %%x in (*.txt) DO java Driver %%x %%x


ECHO Done!

pause
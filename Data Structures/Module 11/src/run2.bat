@echo off

ECHO Running test cases...

FOR %%x in (*) DO (java ShellSort %%x ss-%%x)
FOR %%x in (*) DO java HeapSort %%x hs-%%x

ECHO Done!
ECHO

pause
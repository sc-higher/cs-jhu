@echo off

ECHO Running test cases...

FOR %%x in (*.dat) DO java ShellSort %%x ss-%%x
FOR %%x in (*.dat) DO java HeapSort %%x hs-%%x

ECHO Done!

pause
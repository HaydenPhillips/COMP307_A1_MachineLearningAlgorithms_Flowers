Run main with two different args:
1st arg) The path to "iris-training.dat"
2st arg) The path to "iris-test.dat"

It should run K = 1 and K = 3 automatically.

If you encounter problems check the scanFile method:
ScanFile method reads files only if they contain
the String of the file names e.g. if (s.contains("iris-training.txt")) read file.
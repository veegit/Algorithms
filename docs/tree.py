#!/usr/bin/python
# -*- coding: utf-8 -*-

import os
import sys

class Tree:
    def __init__(self):
        self.dirCount = 0
        self.fileCount = 0

    def register(self, absolute):
        if os.path.isdir(absolute):
            self.dirCount += 1
        else:
            self.fileCount += 1

    def summary(self):
        return str(self.dirCount) + " directories, " + str(self.fileCount) + " files"

    def walk(self, directory, prefix = ""):
        filepaths = sorted([filepath for filepath in os.listdir(directory)])

        for index in range(len(filepaths)):
            if filepaths[index][0] == ".":
                continue

            absolute = os.path.join(directory, filepaths[index])
            self.register(absolute)
            github_base = "https://github.com/veegit/Algorithms/blob/master"
            github_link = "/".join([github_base, directory, filepaths[index]])
            github_md = "[{}]({})<br />".format(filepaths[index],github_link)
            if index == len(filepaths) - 1:
                print(prefix + "└── " + github_md)
                if os.path.isdir(absolute):
                    self.walk(absolute, prefix + "  ")
            else:
                print(prefix + "├── " + github_md)
                if os.path.isdir(absolute):
                    self.walk(absolute, prefix + "│  ")

directory = "src/main/java/com/vee/algorithms"
if len(sys.argv) > 1:
    directory = sys.argv[1]
print("Hierarchy of problems<br />")
print(directory+"<br />")

tree = Tree()
tree.walk(directory)

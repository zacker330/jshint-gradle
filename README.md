Jshint Gradle Plugin
====================

## Installation

determined

## Configuration

1. config jshint options

    create a named `jshintConfig.json` file at `src/main/resources`. 
    
    jshintConfig.json file as below:
    
        {
            "undef":true,
            "unused":true
            
            .
            .
            .
        
        }
        
    [jshint options](http://www.jshint.com/docs/options/)
    
1. config jshint-gradle plugin

   add the jshint closure at build.gradle:

     jshint {
         inputDirs = your_input_dir_path // require
         includes = ["asset/*.js"]      // require
         excludes = ["**/compase/*.js"] // require
         version = '2.4.3' // option. the version of jshint is default 2.4.3 
     }
     
   
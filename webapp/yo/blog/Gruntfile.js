'use strict';

module.exports = function (grunt) {

  // Project configuration.
  grunt.initConfig({
    // Metadata.
    pkg: grunt.file.readJSON('package.json'),
    banner: '/* <%= pkg.name %> - v<%= pkg.version %> - ' +
      '<%= grunt.template.today("yyyy-mm-dd") %>\n' +
      '<%= pkg.homepage ? "* " + pkg.homepage + "\\n" : "" %>' +
      ' * Copyright (c) <%= grunt.template.today("yyyy") %> <%= pkg.name %> */ \n',


    //Cleaning task
    clean: {
      src: ['dist', '.tmp']
    },

    //Copy task
    copy: {
      //Initial folder: app to .tmp
      dist: {
        files: [
          // Copy all app/ files to .tmp/ excepts sass files and scripts
          {
            expand: true,
            cwd: "app",
            src: ['**', '!styles/sass/**', '!scripts/**/*.js'],
            dest: ".tmp"
          }
        ]
      },
      //Final folder: .tmp to dist
      finalDist: {
        files: [
          // Copy all .tmp/ files to dist/
          {
            expand: true,
            cwd: ".tmp",
            src: '**',
            dest: "dist"
          }
        ]
      }
    },

    //SCSS Compile task
    sass: {
      dist: {
        files: [
          // Put compiled css in .tmp/styles
          {
            expand: true,
            cwd: "app/styles/sass",
            src: "**/*.scss",
            dest: ".tmp/styles",
            ext: '.css'
          }
        ]
      }
    },

    //Javascript minify task
    uglify: {
      options: {
        banner: '<%= banner %>'
      },
      //Append .min.js extension and move to .tmp/
      dist: {
        expand: true,
        cwd: 'app',
        src: ['scripts/**/*.js'],
        dest: '.tmp',
        ext: '.js'
      }
    },


    //Add revision prefix
    rev: {
      options: {
        algorithm: 'md5',
        length: 8
      },
      assets: {
        expand: true,
        // Copy form .tmp/ to .tmp/ (source files are deleted)
        cwd: '.tmp',
        dest: '.tmp',
        // Note: index.html and admin.html must not be prefixed.
        src: ['*.html', '!{index,admin}.html', 'images/*', 'styles/*.css', 'scripts/{,controllers/}*.js', 'i18n/*.json'],
        filter: 'isFile'
      }
    },

    //Update old files matches to new ones
    usemin: {
      html: '.tmp/**/*.html',
      css: '.tmp/**/*.css',

      // Custom matchings
      htmlextra: '.tmp/**/*.html',
      js: '.tmp/scripts/{,controllers/}*.js',
      options: {
        patterns: {
          htmlextra: [
            [/src="'([^"']+)'"/gm, "Update the HTML angular includes with new HTML filenames"]
          ],
          js: [
            [/['"]([^"']+)["']/gm, 'Update the JS with the new HTML filenames']
          ]
        }
      }
    },

    //Connect task
    connect: {
      options: {
        port: 9000,
        livereload: 35729,
        hostname: 'localhost'
      },
      livereload: {
        options: {
          open: 'localhost',
          base: ['dist', 'app']
        }
      }
    },

    //Watch task
    watch: {
      styles: {
        files: ['app/**'],
        tasks: ['default']
      },
      livereload: {
        options: {
          livereload: true
        },
        files: [
          'app/**'
        ]
      }
    }

  });

  // These plugins provide necessary tasks.
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-rev');
  grunt.loadNpmTasks('grunt-usemin');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-sass');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-connect');

  // Default task.
  grunt.registerTask('default', ['clean', 'copy:dist', 'sass', 'uglify', 'rev', 'usemin', 'copy:finalDist']);

  // Serve task.
  grunt.registerTask('serve', function () {
    grunt.task.run(['clean', 'copy:dist', 'sass', 'uglify', 'rev', 'usemin', 'copy:finalDist', 'connect', 'watch']);
  });
};

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
      all: ['dist', '.tmp'],
      tmp: ['.tmp'],
      styles: ['dist/styles'],
      scripts: ['dist/scripts'],
      i18n: ['dist/i18n'],
      staticContent: ['dist/**/*.html', 'dist/images/*']
    },

    //Copy task
    copy: {
      dist: {
        expand: true,
        cwd: "app",
        src: ['**', '!styles/sass/**', 'scripts/**/*.js', '**/*.html', 'images/*'],
        dest: "dist"
      },
      assets: {
        expand: true,
        cwd: "bower_components",
        src: '**',
        dest: "dist/bower_components"
      }
    },

    //SCSS Compile task
    sass: {
      files: // Put compiled css in .tmp/styles
      {
        expand: true,
        cwd: "app/styles/sass",
        src: "**/*.scss",
        dest: ".tmp/styles",
        ext: '.css'
      }
    },

    //CSS Minify task
    cssmin: {
      files: // Minify css to dist/ destination
      {
        expand: true,
        cwd: ".tmp/styles",
        src: "**/*.css",
        dest: "dist/styles"
      }
    },

    //JSON Minify task
    'json-minify': {
      build: {
        files: 'dist/i18n/**/*.json'
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
        dest: 'dist'
      }
    },

    //Add revision prefix
    filerev: {
      options: {
        encoding: 'utf8',
        algorithm: 'md5',
        length: 8,
        keepOriginalFiles: false
      },
      files: {
        expand: true,
        cwd: 'dist',
        dest: 'dist',
        // Note: index.html and admin.html must not be prefixed.
        src: ['**/*.html', '!{index,admin}.html', 'images/*', 'scripts/{,controllers/}*.js', 'styles/*.css', 'i18n/*.json'],
        filter: 'isFile'
      }
    },

    //Update old files matches to new ones
    usemin: {
      html: 'dist/**/*.html',
      css: 'dist/**/*.css',
      // Custom matchings
      htmlextra: 'dist/**/*.html',
      js: 'dist/scripts/{,controllers/}*.js',
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
    
    //Bower install task
    bowerInstall: {

      target: {
        // Files updated when running bower install
        src: [
          'app/**/*.html',   // .html support...
        ]
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
          base: ['dist']
        }
      }
    },

    //Watch task
    watch: {
      app: {
        files: ['app/**'],
        tasks: ['default']
      },
      livereload: {
        options: {
          livereload: true
        },
        files: ['app/**']
      }
    }

  });

  // These plugins provide necessary tasks.
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-filerev');
  grunt.loadNpmTasks('grunt-usemin');
  grunt.loadNpmTasks('grunt-json-minify');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-sass');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-connect');
  grunt.loadNpmTasks('grunt-bower-install');

  // Default task.
  grunt.registerTask('default', ['clean:all', 'bowerInstall', 'copy:dist', 'sass', 'cssmin', 'json-minify', 'filerev', 'usemin', 'copy:assets', 'clean:tmp']);

  // Serve task.
  grunt.registerTask('serve', function () {
    grunt.task.run(['default', 'connect', 'watch']);
  });
};

# Dropwizard Ember Data Demo (DEDD)

A robust demonstrator of [Ember Data](https://github.com/emberjs/data) against a [Dropwizard](http://dropwizard.codahale.com) RESTful API.

It is intended to demonstrate the workflows associated with creating a self-contained Ember.js front-end served by a
Dropwizard back end. Thus you will see how to use Grunt.js as a kind of Maven for the front-end, and the role of Dropwizard
in both serving the JavaScript application and handling the REST queries that subsequently come from the user interaction.

It will demonstrate a simple blog with posts and comments. These are held in a simple in-memory cache. All data is
served through a simple REST API that adheres to the requirements of the [Ember Data RESTAdapter](http://emberjs.com/guides/models/the-rest-adapter/).

It uses the following libraries:

# Client side

Provided:
* Ember 1.0.0-rc.3
* Ember Data 3caadcc (2013-04-26 11:07:32 -0700)
* Handlebars 1.0.0-rc.3
* jQuery 1.9.1
* Bootstrap

Needs to be available/installed:

* Node.js (to service Grunt.js)
* Grunt.js

## Installation notes for Node.js

Follow the [node.js installation instructions](http://nodejs.org/).

### Mac (Homebrew)

You will need [Homebrew](https://github.com/mxcl/homebrew) installed, then just issue

```
brew install node
```

## Installation notes for Grunt.js

Once Node.js is in place, you can use the Node Package Manager (NPM) to get Grunt (and other JS goodies). Follow the [Grunt installation instructions](http://gruntjs.org/).

```
npm install -g grunt-cli
```

You may encounter access rights issues, in which case use `sudo` but remember to `chown` your `~/.npm` directory contents.

### Mac

After installing Grunt you might need to adjust your PATH in ~/.bashrc to include the Node.js NPM executable files:

```
export PATH=/usr/local/share/npm/bin:$PATH
```

You can learn more about Grunt [from this useful introduction](http://net.tutsplus.com/tutorials/javascript-ajax/meeting-grunt-the-build-tool-for-javascript/).

# Server side

Provided:

* Dropwizard

Needs to be available/installed:

* Maven 3.0.3+
* Java

# How do I build it?

The front and back ends are both separate applications in their own right. The front end is served up by the back end.

## Building the front end with Grunt.js

The front end user interface is designed around Ember.js and as a result will require some supporting frameworks to enable
an efficient development workflow. Typically this will involve cursory knowledge of `Node.js` and `Grunt`](http://gruntjs.com/)
confined to a bit of configuration of JSON files.

## Install dependencies using `npm`

Think of `npm` as the package manager of JavaScript. You can get all the dependencies using

```
cd <project root>/src/main/ember
npm install
```

This will install the development dependencies listed in the `package.json` file and store them locally in `node_modules`.
The `node_modules` directory is not under source control (see the local `.gitignore`. This should be a one-off operation
unless you're doing experimental development with lots of modules in which case browsing [the NPM repository](https://npmjs.org)
is a good idea.

## Build development version of app (from command line)

Once all dependencies are in place run Grunt. The `Gruntfile.js` contains more information about what is going on.

```
cd <project root>/src/main/ember
grunt
```

## Build development version of app (from Intellij)

Intellij can be configured to run Node.js applications such as Grunt. Use the usual Intellij plugin installation process
for the `NodeJS` plugin.

### Mac

Create a runtime configuration with the following parameters:

```
Name: Grunt
Path to Node: /usr/local/bin/node
Working Directory: src/main/ember
Path to Node App JS File: /usr/local/share/npm/bin/grunt
```

## View the app (no backend)

If you just want to run up the front end without any Dropwizard RESTful API provision (perhaps for simple CSS layout
work) simply do the Grunt work as described earlier and then open the file `src/main/ember/build/index.html`. As changes
are made to the application Grunt will automatically regenerate the appropriate supporting files so that you can keep a
good flow going.

## View the app (with Dropwizard)

If you want to use Dropwizard to provide a backend then simply run up `AppService.main()` as normal then run the
Grunt process as a second runtime configuration (you'll have both Java and Grunt chugging away).

# What am I seeing?

If you see the text "This is the post you're looking for!" then you're in business.

# Nope not seeing that...

You'll need to do some debugging.

* Most likely you're not viewing `index.html` through a server (same origin problem)
* Don't go changing the `src/main/ember/dependencies/ember*.js` files - compatibility in the release candidates is fraught with hazard
* Check the JavaScript console in your browser. Chrome and Firefox have comprehensive debugging tools.

# Yay! I'm in business! What's going on?

Let's work it back from the front end to the server

1. The file `index.html` got loaded and a bunch of scripts were initialized
2. The section script with `data-template-name="application"` is the HTML output for the Ember application
3. The section script with `data-template-name="index"` is the HTML template for the post

# OK, and where's the JavaScript?

All the magic happens in `app.js` take a look at the comments and you'll see how it fits together.

1. The application will log to the browser console
2. There is a model called Post with a `title` and `body` linked to a data store
3. There is a simple route that only gets the Post with id=1 (the path `posts` is implied through Ember naming conventions)
4. The `RESTAdapter` is configured to create URL paths using the template `/api/posts/{id}.json` which makes the simulated RESTful API easier
5. The data store uses Ember Data revision 12 and references the `RESTAdapter`

# Well that was easy! What next?

For a much bigger scale application involving a [Maven](http://maven.apache.org)-built [Dropwizard](http://dropwizard.codahale.com) back end combined
with a [Grunt.js](http://gruntjs.com)-built front end see my other public repos (TODO).

# Where does the ASCII art for the banner come from?

I used the excellent [TAAG site](http://patorjk.com/software/taag).
/*
 * This is an Ember application. It's built using a
 * neuter task (see this project's Gruntfile for what that means).
 *
 * `require`s in this file will be stripped and replaced with
 * the string contents of the file they refer to wrapped in
 * a closure.
 *
 * Each file contains its own commenting, so feel free to crack
 * them open if you want more information about what is going on.
 */

/*
 * These are the dependencies for an Ember application
 * and they have to be loaded before any application code.
 */
require('dependencies/jquery');

/*
 * Since we're precompiling our templates, we only need the
 * handlebars-runtime microlib instead of the
 * entire handlebars library and its string parsing functions.
 */
require('dependencies/handlebars');

/* Ember for client side MVC */
require('dependencies/ember');

/* Ember Data for automatic persistence to a REST API */
require('dependencies/ember-data');

/*
 this file is generated as part of the build process.
 If you haven't run that yet, you won't see it.

 It is excluded from git commits since it's a
 generated file.
 */
require('dependencies/compiled/templates');

// Create the application
App = Ember.Application.create({
  LOG_TRANSITIONS: true,
  rootElement: '#dedd'
});

App.store  = DS.Store.create({
  adapter:  "DS.RESTAdapter",
  revision: 12
});

/*
 * Model layer.
 * Ember.Object itself provides most of what
 * model layers elsewhere provide. Since TodoMVC
 * doesn't communicate with a server, plain
 * Ember.Objects will do.
 */
require('app/models/post');

/*
 * Views layer.
 * You'll notice that there are only a few views.
 * Ember accomplishes a lot in its templates and
 * Views are only necessary if you have view-specific
 * programming to do.
 */
require('app/views/post_summary');

/*
 * Controller layer.
 * Controllers wrap objects and provide a place
 * to implement properties for display
 * whose value is computed from the content of the
 * controllers wrapped objects.
 */
require('app/controllers/posts_controller');
require('app/controllers/post_controls_controller');
require('app/controllers/selected_post_controller');

/*
 * States (i.e. Routes)
 * Handles serialization of the application's current state
 * which results in view hierarchy updates. Responds to
 * actions.
 */
require('app/routes/router');
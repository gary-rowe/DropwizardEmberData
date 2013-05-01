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

// Our application (which will track transitions in the browser console)
var App = Ember.Application.create({
  LOG_TRANSITIONS: true,
  rootElement: '#dedd'
});

// has a model for a "Post" linked to an Ember data store (DS)
App.Post = DS.Model.extend({
  title: DS.attr('string'),
  body: DS.attr('string')
});

// and a simple default router which just returns the result of querying the Post model with a specific ID
App.IndexRoute = Ember.Route.extend({
  model: App.Post.find()
});

// which is served from the same origin under /
App.Adapter = DS.RESTAdapter.extend({
  url: 'http://localhost:8080'
});

// by means of a data store linked to a particular REST adapter
App.Store = DS.Store.extend({
  revision: 12,
  adapter: App.Adapter.create({})
});

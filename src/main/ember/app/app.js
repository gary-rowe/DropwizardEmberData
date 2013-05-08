///*
// * This is an Ember application. It's built using a
// * neuter task (see this project's Gruntfile for what that means).
// *
// * `require`s in this file will be stripped and replaced with
// * the string contents of the file they refer to wrapped in
// * a closure.
// *
// * Each file contains its own commenting, so feel free to crack
// * them open if you want more information about what is going on.
// */
//
///*
// * These are the dependencies for an Ember application
// * and they have to be loaded before any application code.
// */
require('dependencies/jquery/jquery');
//
///*
// * Since we're precompiling our templates, we only need the
// * handlebars-runtime microlib instead of the
// * entire handlebars library and its string parsing functions.
// */
require('dependencies/ember/handlebars-1.0.0-rc.3');
//
///* Ember for client side MVC */
require('dependencies/ember/ember-1.0.0-rc.3');
//
///* Ember Data for automatic persistence to a REST API */
require('dependencies/ember/ember-data');
//
///*
// this file is generated as part of the build process.
// If you haven't run that yet, you won't see it.
//
// It is excluded from git commits since it's a
// generated file.
// */
require('dependencies/compiled/templates');
//
//// Create the application
//App = Ember.Application.create({
//  LOG_TRANSITIONS: true,
//  rootElement: '#dedd'
//});
//
//// Map all requests to /api
//App.Adapter = DS.RESTAdapter.extend({
//  url: '/api'
//});
//
//// and attach it to the main App
//App.store = DS.Store.extend({
//  revision: 12,
//  adapter: App.Adapter.create({})
//});
//
///*
// * Model layer.
// * Ember.Object itself provides most of what
// * model layers elsewhere provide. Since TodoMVC
// * doesn't communicate with a server, plain
// * Ember.Objects will do.
// */
//require('app/models/post');
//
///*
// * Views layer.
// * You'll notice that there are only a few views.
// * Ember accomplishes a lot in its templates and
// * Views are only necessary if you have view-specific
// * programming to do.
// */
//require('app/views/post_summary');
//require('app/views/pagination');
//
///*
// * Controller layer.
// * Controllers wrap objects and provide a place
// * to implement properties for display
// * whose value is computed from the content of the
// * controllers wrapped objects.
// */
//require('app/controllers/posts_controller');
//require('app/controllers/selected_post_controller');
//
///*
// * States (i.e. Routes)
// * Handles serialization of the application's current state
// * which results in view hierarchy updates. Responds to
// * actions.
// */
//require('app/routes/router');
//
///*
// * Mixins
// * Provides extra functionality not found in the core libraries
// */
//require('app/mixins/pagination');

App = Ember.Application.create();

App.PostController = Ember.ArrayController.extend(Ember.PaginationMixin, {
  itemsPerPage: 2
});

App.PaginationView = Ember.View.extend({
  templateName: 'pagination',
  tagName: 'li',

  page: function() {
    return Ember.Object.create({id: this.get('content.page_id')});
  }.property()
});

App.Router.map(function(match) {
  this.resource("post", { path: "/" }, function() {
    this.route("page", { path: "/page/:page_id" });
  });
});

App.PostPageRoute = Ember.Route.extend({
  model: function(params) {
    return Ember.Object.create({id: params.page_id});
  },
  setupController: function(controller, model) {
    this.controllerFor('post').set('selectedPage', model.get('id'));
  }
});

App.PostRoute = Ember.Route.extend({
  model: function(params) {
    this.controllerFor('post').set('selectedPage', 1);
    return App.Post.find();
  }
});

App.Post = DS.Model.extend({
  username: DS.attr('string')
});

App.Store = DS.Store.extend({
  revision: 12,
  adapter: 'DS.FixtureAdapter'
});

App.Post.FIXTURES = [
  {id:1,username:'dave one'},
  {id:2,username:'dave two'},
  {id:3,username:'dave three'},
  {id:4,username:'dave four'},
  {id:5,username:'dave five'},
  {id:6,username:'dave six'},
  {id:7,username:'dave seven'}
];

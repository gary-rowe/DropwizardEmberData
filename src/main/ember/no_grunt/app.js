// Application
var App = Ember.Application.create({
  LOG_TRANSITIONS:true,
  rootElement:'#dedd'
});

// Store
App.Adapter = DS.RESTAdapter.extend({
  namespace:'api'
});
App.Store = DS.Store.extend({
  revision:12,
  adapter:App.Adapter.create({})
});

// Routes
App.Router.map(function (match) {
  this.route("index", {path: "/"}); // IndexController -> "/" via a generated "App.IndexRoute"

  this.resource("posts", { path:"/posts" }, function () {   // PostsController -> "/posts" via "PostsRoute"
    this.route("selectedPage", { path:"/page/:page_id" }); // PostsSelectedPageController -> "/posts/page/{id}"
    this.route("selectedPost", { path:"/post/:post_id" }); // PostsSelectedPostController -> "/posts/post/{id}"
  });
});

// Override the generated App.IndexRoute with this one
App.IndexRoute = Ember.Route.extend({
    redirect: function() {
        this.transitionTo("posts"); // Make this route redirect to the named "posts" resource
    }
});

App.PostsRoute = Ember.Route.extend({
  model:function (params) {
    this.controllerFor('posts').set('selectedPage', 1);
    return App.Post.find();
  }
});

App.PostsSelectedPageRoute = Ember.Route.extend({
  model:function (params) {
    // Create a model containing just the page ID
    return Ember.Object.create({id:params.page_id});
  },
  setupController:function (controller, model) {
    // Find the PostController and set its selected page to the page ID
    // This is used by the PaginationMixin
    this.controllerFor('posts').set('selectedPage', model.get('id'));
  }
});

App.PostsSelectedPostRoute = Ember.Route.extend({
  model: function(params) {
    return App.Post.find(params.post_id); // Returns a specific Post by ID
  }
});

// Models
App.Post = DS.Model.extend({
  title:DS.attr('string'),
  summary:DS.attr('string'),
  body:DS.attr('string')
});

// Views
App.PaginationView = Ember.View.extend({
  templateName:'pagination',
  tagName:'li',
  classNameBindings: ['activeCurrent:active:'],

  /*
   * Computed property
   * Returns a simple model containing the page ID
   */
  page:function () {
    return Ember.Object.create({id:this.get('content.page_id')});
  }.property('page_id'),

  /*
   * Computed property
   * Returns true if the selected page ID is the same as the current page ID so
   * that the UI can show highlighting
   */
  activeCurrent:function () {
    var currentPage = parseInt(this.get('content.page_id'));
    var selectedPage = this.get('selectedPage');
    return selectedPage == currentPage;
  }.property('content.page_id','selectedPage')

});

// Controllers
// Extends the PaginationMixin which provides standard methods for
// page navigation for use by the views
App.PostsController = Ember.ArrayController.extend(Ember.PaginationMixin, {
  itemsPerPage:3,
  pagesPerControl:5
});

App.SelectedPostController = Ember.ArrayController.extend(Ember.PaginationMixin, {
  itemsPerPage:3,
  pagesPerControl:5
});

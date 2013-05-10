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
  this.resource("post", { path:"/" }, function () {
    this.route("page", { path:"/page/:page_id" });
  });
});

App.PostPageRoute = Ember.Route.extend({
  model:function (params) {
    // Create a model containing just the page ID
    return Ember.Object.create({id:params.page_id});
  },
  setupController:function (controller, model) {
    // Find the PostController and set its selected page to the page ID
    // This is used by the PaginationMixin
    this.controllerFor('post').set('selectedPage', model.get('id'));
  }
});

App.PostRoute = Ember.Route.extend({
  model:function (params) {
    this.controllerFor('post').set('selectedPage', 1);
    return App.Post.find();
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
App.PostController = Ember.ArrayController.extend(Ember.PaginationMixin, {
  itemsPerPage:5,
  pagesPerControl:9
});




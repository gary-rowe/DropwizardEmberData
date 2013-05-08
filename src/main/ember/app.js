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
App.Router.map(function () {
  this.route("index", {path:"/"});
  this.resource("posts", {path:"/posts"}, function () {
    this.route("selectedPost", {path:":post_id"})
  });
});

// Application default route (show the master Post item list)
App.IndexRoute = Ember.Route.extend({
  redirect:function () {
    this.transitionTo('posts');
  }
});

App.PostsRoute = Ember.Route.extend({
  model: function(params) {
    return App.Post.find();
  },
  setupController:function (controller, model) {
    // Set the items to the `data` property for use in the controller
    controller.set('posts.data', model.toArray());
  }
});

App.SelectedPostRoute = Ember.Route.extend({
  model:function (params) {
    return App.Post.find(params.post_id);
  }
});

// Models
App.Post = DS.Model.extend({
  title:DS.attr('string'),
  summary:DS.attr('string'),
  body:DS.attr('string')
});

// Views
App.PaginationView = VG.Views.Pagination.extend({
  numberOfPages:15
});
App.TableHeaderView = VG.Views.TableHeader.extend({
  template:Ember.Handlebars.compile('{{#if view.isCurrent}}<i {{bindAttr class="view.isAscending:icon-sort-up view.isDescending:icon-sort-down"}}></i>{{/if}}{{view.text}}')
});

// Controllers
App.PostsController = Ember.Controller.extend({
  posts:Ember.ArrayController.createWithMixins(VG.Mixins.Pageable, {
    perPage:5
  })
});


// TODO Merge this in

PersonApp.PersonController = Ember.ArrayController.extend(Ember.PaginationMixin, {
    itemsPerPage: 2
});

PersonApp.PaginationView = Ember.View.extend({
    templateName: 'pagination',
    tagName: 'li',

    page: function() {
        return Ember.Object.create({id: this.get('content.page_id')});
    }.property()
});

PersonApp.Router.map(function(match) {
    this.resource("person", { path: "/" }, function() {
        this.route("page", { path: "/page/:page_id" });
    });
});

PersonApp.PersonPageRoute = Ember.Route.extend({
    model: function(params) {
        return Ember.Object.create({id: params.page_id});
    },
    setupController: function(controller, model) {
        this.controllerFor('person').set('selectedPage', model.get('id'));
    }
});

PersonApp.PersonRoute = Ember.Route.extend({
    model: function(params) {
        this.controllerFor('person').set('selectedPage', 1);
        return PersonApp.Person.find();
    }
});

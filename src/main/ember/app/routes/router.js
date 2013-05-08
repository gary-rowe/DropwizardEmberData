App.Router.map(function () {

//  this.route("index", {path: "/"});

//  this.resource("posts", {path: "/posts"}, function () {
//    this.route("selectedPost", {path: ":post_id"})
//
//  });

  // TODO Figure out the merging here
  this.resource("post", { path: "/" }, function () {
    this.route("page", { path: "/page/:page_id" });
  });

});

//App.IndexRoute = Ember.Route.extend({
//  redirect: function () {
//    this.transitionTo('posts');
//  }
//});

// Post

// Provides access to all the Posts
//App.PostsRoute = Ember.Route.extend({
//  model: function () {
//    return App.Post.find();
//  }
//});

App.PostPageRoute = Ember.Route.extend({
  model: function(params) {
    // Create a model out of the page ID
    return Ember.Object.create({id: params.page_id});
  },
  setupController: function(controller, model) {
    // Use the page model
    this.controllerFor('post').set('selectedPage', model.get('id'));
  }
});

App.PostRoute = Ember.Route.extend({
  model: function(params) {
    // Start with the first page
    this.controllerFor('post').set('selectedPage', 1);
    return App.Post.find();
  }
});

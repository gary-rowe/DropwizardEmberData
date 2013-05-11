// Routes
App.Router.map(function (match) {
  // IndexRoute/Controller -> "/" 
  this.route("index", {path: "/"});

  // A resource() groups many related route() entries
  // PostsRoute/Controller -> "/posts" 
  this.resource("posts", { path:"/posts" }, function () {

    // PostsSelectedPageRoute/Controller -> "/posts/page/{id}" 
    this.route("selectedPage", { path:"/page/:page_id" });

    // PostsSelectedPostRoute/Controller -> "/posts/post/{id}"
    this.route("selectedPost", { path:"/post/:post_id" });
  });
});

// Override the generated App.IndexRoute with this one
App.IndexRoute = Ember.Route.extend({
  redirect: function() {
    // Make this route redirect to the named "posts" route
    this.transitionTo("posts");
  }
});

// Handles /posts
App.PostsRoute = Ember.Route.extend({
  model:function (params) {
    // Set a default page number
    this.controllerFor('posts').set('selectedPage', 1);
    // Get the posts from the Store
    return App.Post.find();
  }
});

// Handles /posts/page/:page_id
App.PostsSelectedPageRoute = Ember.Route.extend({
  model:function (params) {
    // Create a model on the fly containing just the page ID
    return Ember.Object.create({id:params.page_id});
  },
  setupController:function (controller, model) {
    // Find the PostsController and set its selected page to the page ID
    // This is used by the PaginationMixin
    this.controllerFor('posts').set('selectedPage', model.get('id'));
  }
});

// Handles /posts/post/:post_id
App.PostsSelectedPostRoute = Ember.Route.extend({
  model: function(params) {
    // Find a Post with the given ID
    return App.Post.find(params.post_id);
  }
});
App.Router.map(function() {
	this.route("index", {path: "/"});
    this.resource("posts", {path: "/posts"}, function() {
        this.route("selectedPost", {path: ":post_id"})
    });
});

App.IndexRoute = Ember.Route.extend({
    redirect: function() {
        this.transitionTo('posts');
    }
});

// Provides access to all the Posts
App.PostsRoute = Ember.Route.extend({
    model: function() {
        return App.Post.find();
    }
});
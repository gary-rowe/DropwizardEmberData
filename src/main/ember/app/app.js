// Our application (which will track transitions in the browser console)
var App = Ember.Application.create({
  LOG_TRANSITIONS: true
});

// has a model for a "Post" linked to an Ember data store (DS)
App.Post = DS.Model.extend({
  title: DS.attr('string'),
  body: DS.attr('string')
});

// and a simple default router which just returns the result of querying the Post model with a specific ID
App.IndexRoute = Ember.Route.extend({
  model: function() {
    return App.Post.find(1);
  }
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

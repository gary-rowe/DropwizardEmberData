App.PostsController = Ember.ArrayController.extend(Ember.PaginationMixin, {
  itemsPerPage: 2
});

App.PostsSelectedPostController = Ember.ObjectController.extend({});
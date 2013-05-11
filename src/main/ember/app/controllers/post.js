// Extends the PaginationMixin which provides standard methods for
// page navigation for use by the views
App.PostsController = Ember.ArrayController.extend(Ember.PaginationMixin, {
  itemsPerPage:3,
  pagesPerControl:5
});
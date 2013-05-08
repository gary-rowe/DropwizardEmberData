App.PaginationView = Ember.View.extend({
  templateName: 'pagination',
  tagName: 'li',

  page: function() {
    return Ember.Object.create({id: this.get('content.page_id')});
  }.property()
});
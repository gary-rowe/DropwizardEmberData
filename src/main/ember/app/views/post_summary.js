App.PostSummaryView = Ember.View.extend({
  tagName: 'post',
  attributeBindings: ['src'],
  classNames: ['summaryItem'],
  classNameBindings: 'isSelected',

  isSelected: function() {
    return this.get('content.id') ===
      this.get('controller.controllers.postsSelectedPost.content.id');
  }.property('controller.controllers.postsSelectedPost.content', 'content')
});
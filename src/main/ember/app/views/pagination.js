App.PaginationView = Ember.View.extend({
  // This View backs the pagination template
  templateName:'pagination',
  // It builds <li> tags
  tagName:'li',
  // It will set class="active" or nothing depending on the activeCurrent property
  classNameBindings: ['activeCurrent:active:'],

  /*
   * Computed property
   * Returns a simple model containing the page ID
   */
  page:function () {
    // The View has a default "content" from its Controller
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

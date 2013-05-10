var get = Ember.get, set = Ember.set;

Ember.PaginationMixin = Ember.Mixin.create({

  pages:function () {

    var availablePages = this.get('availablePages');
    var currentPage = this.get('currentPage');
    var pagesPerControl = this.get('pagesPerControl');
    var pages = [];
    var page;
    var start = 0;
    var end = availablePages;

    var offset = Math.ceil(pagesPerControl / 2);

    // Scroll? (Start at page 2, 3 etc)
    if (currentPage - offset > 0) {
      start = currentPage - offset;
    }
    // End point
    if (start + pagesPerControl < availablePages) {
      end = start + pagesPerControl;
    }
    // Reached end?
    if (start + pagesPerControl >= availablePages) {
      start = availablePages - pagesPerControl;
    }

    for (var i = start; i < end; i++) {
      page = i + 1;
      pages.push({ page_id:page.toString() });
    }

    return pages;

  }.property('currentPage', 'availablePages'),

  currentPage:function () {

    return parseInt(this.get('selectedPage'), 10) || 1;

  }.property('selectedPage'),

  nextPage:function () {

    var nextPage = this.get('currentPage') + 1;
    var availablePages = this.get('availablePages');

    if (nextPage <= availablePages) {
      return Ember.Object.create({id:nextPage});
    } else {
      return Ember.Object.create({id:this.get('currentPage')});
    }

  }.property('currentPage', 'availablePages'),

  prevPage:function () {

    var prevPage = this.get('currentPage') - 1;

    if (prevPage > 0) {
      return Ember.Object.create({id:prevPage});
    } else {
      return Ember.Object.create({id:this.get('currentPage')});
    }

  }.property('currentPage'),

  firstPage:function () {

    return "1";

  }.property(),

  availablePages:function () {

    return Math.ceil((this.get('content.length') / this.get('itemsPerPage')) || 1);

  }.property('content.length','itemsPerPage'),

  paginatedContent:function () {

    var selectedPage = this.get('selectedPage') || 1;
    var upperBound = (selectedPage * this.get('itemsPerPage'));
    var lowerBound = (selectedPage * this.get('itemsPerPage')) - this.get('itemsPerPage');
    var models = this.get('content');

    return models.slice(lowerBound, upperBound);

  }.property('selectedPage', 'content.@each'),

  /**
   * Computed property to determine if the previous page link should be disabled or not.
   * @return {Boolean}
   */
  disablePrev:function () {
    return this.get('currentPage') == 1;
  }.property('currentPage'),

  /**
   * Computed property to determine if the next page link should be disabled or not.
   * @return {Boolean}
   */
  disableNext:function () {
    return this.get('currentPage') == this.get('availablePages');
  }.property('currentPage', 'availablePages')

});

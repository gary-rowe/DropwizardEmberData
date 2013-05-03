App.PostControlsController = Ember.Controller.extend({
    needs: ['posts', 'postsSelectedPost'],
    slideshowTimerId: null,

    playSlideshow: function() {
        console.log('playSlideshow');
        var controller = this;
        controller.nextPost();
        this.set('slideshowTimerId', setInterval(function() {
            Ember.run(function() {
                controller.nextPost();
            });
        }, 4000));
    },

    stopSlideshow: function() {
        console.log('stopSlideshow');
        clearInterval(this.get('slideshowTimerId'));
        this.set('slideshowTimerId', null);
    },

    nextPost: function() {
        console.log('nextPost');
        this.get('controllers.posts').nextPost();
    },

    prevPost: function() {
        console.log('prevPost');
        this.get('controllers.posts').prevPost();
    }
});
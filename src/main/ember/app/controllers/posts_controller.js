App.PostsController = Ember.ArrayController.extend({
    needs: ['postsSelectedPost'],

    selectPostAction: function(post) {
        this.set('selectedPost', post);
    },

    nextPost: function() {
        var selectedPost = null;
        if (!this.get('controllers.postsSelectedPost.content')) {
            this.transitionToRoute("posts.selectedPost", this.get('content.firstObject'));
        } else {
            var selectedIndex = this.findSelectedItemIndex();

            if (selectedIndex >= (this.get('content.length') - 1)) {
                selectedIndex = 0;
            } else {
                selectedIndex++;
            }

            this.transitionToRoute("posts.selectedPost", this.get('content').objectAt(selectedIndex))
        }
    },

    prevPost: function() {
        console.log('PostListController prevPost');
        if (!this.get('controllers.postsSelectedPost.content')) {
            this.transitionToRoute("posts.selectedPost", this.get('content.lastObject'));
        } else {
            var selectedIndex = this.findSelectedItemIndex();

            if (selectedIndex <= 0) {
                selectedIndex = this.get('content.length') - 1;
            } else {
                selectedIndex--;
            }

            this.transitionToRoute("posts.selectedPost", this.get('content').objectAt(selectedIndex))
        }
    },

    findSelectedItemIndex: function() {
        var content = this.get('content');
        var selectedPost = this.get('controllers.postsSelectedPost.content');

        for (index = 0; index < content.get('length'); index++) {
            if (this.get('controllers.postsSelectedPost.content') === content.objectAt(index)) {
                return index;
            }
        }

        return 0;
    }
});
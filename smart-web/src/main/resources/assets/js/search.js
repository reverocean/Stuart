var search_url = "lessThan/";
var recommend_url = "recommend/";

var Stuart = {
    init : function(){
        $("#search_customers").keypress(function(event) {
                                 if ( event.which == 13 ) {
                                     Stuart.search($(this).val());
                                  }
                               });
    },

    search : function(limit){
        limit |= 3;
        search_url += limit;
        $.get(search_url, Stuart.handle_search_response, "JSON")
    },

    handle_search_response : function(customers){
        var template = "<h3><a>${name}</a><span class='brands'>Current Brands: {{each brands}}${$value} {{/each}}</span></h3><div><p id='brands_for_${customerId}'></p></div>";
        $.template( "customerTemplate", template );

        var customers_container = $("#customers_container");
        customers_container.empty();
        customers_container.html("<div id='accordion'></div>");
        $.each(customers, function(index, customer){
            $.tmpl("customerTemplate", customer).appendTo("#accordion")
        });
        $("#accordion").accordion({
        	heightStyle: "content"
        });
    }
};

var Recommendation = {
    recommend: function(id){

    }
};


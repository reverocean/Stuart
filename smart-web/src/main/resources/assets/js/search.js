var Stuart = {
    init : function(){
        $("#search_customers").keypress(function(event) {
                                 if ( event.which == 13 ) {
                                     Stuart.search($(this).val());
                                  }
                               });
    },

    search : function(limit){
        var search_url = "lessThan/";
        limit |= 3;
        search_url += limit;
        $.get(search_url, Stuart.handle_search_response, "JSON")
    },

    handle_search_response : function(customers){
        var template = "<h3><a name='customer_name' customer_id='${customerId}'>${name}</a><span class='brands'>Current Brands: {{each brands}}${$value} {{/each}}</span></h3><div><p id='brands_for_${customerId}'></p></div>";
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

        Stuart.first_customer_recommend_brands(customers);
    },

    first_customer_recommend_brands : function(customers){
        if(customers.length > 0){
            Recommendation.recommend(customers[0].customerId);
        }
    }
};

var Recommendation = {
    init : function(){
        $("#customers_container").on("click", "a", function(){
            Recommendation.recommend($(this).attr("customer_id"));
        })
    },

    recommend: function(id){
        var recommend_url = "recommend/";
        recommend_url += id;
        $.get(recommend_url, function(brands){
            $("#brands_for_" + id).html(brands.join(', '))
        }, "JSON")
    }
};


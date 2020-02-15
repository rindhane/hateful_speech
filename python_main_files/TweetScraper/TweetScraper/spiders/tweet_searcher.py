import json
import scrapy 
from scrapy.selector import Selector
from .tweet_handles import queries
from ..items import TweetItem


class tweet_searcher(scrapy.Spider):
    name="tweet_searcher"
    
    def get_headers(self,query):
        self.headers = {
            'Accept': 'application/json, text/javascript, */*; q=0.01',
            'Referer': f'https://twitter.com/{query}',
            'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.1.2 Safari/603.3.8',
            'X-Twitter-Active-User': 'yes',
            'X-Requested-With': 'XMLHttpRequest',
            'Accept-Language': 'en-US'
            }
        return self.headers 

    def get_url(self,query):
        main_url="https://twitter.com/i/search/timeline?l=en&f=tweets"
        query_string="&q=(from:%s)&src=typed"
        self.query=query
        self.position_string="&max_position=%s"
        self.max_position=""
        self.url=main_url+query_string+self.position_string
        headers=self.get_headers(query)
        return self.url % (self.query, self.max_position)

    
    def start_requests(self):
        for query in queries():
            url= self.get_url(query)
            self.page=int(getattr(self, "page",2))
            yield scrapy.Request(url=url,headers=self.headers,callback=self.parse_page)

    def parse_page (self, response):
        data=json.loads(response.body.decode("utf-8"))
        self.max_position=data["min_position"]
        self.page=self.page-1
        yield from self.parse_tweets(data['items_html'])
        

    def parse_tweets (self,html):
        sem=Selector(text=html)
        stream=sem.xpath('//li[@data-item-type="tweet"]').getall()
        for items in stream:
            sel =Selector(text=items)
            a=sel.xpath('//p/text()').getall()
            a.extend(sel.xpath('//p//b/text()').getall())
            a=" ".join(a)
            min_id=sel.css('.tweet').attrib['data-permalink-path']
            yield TweetItem({"loc":min_id,"tweet":a})
        if self.page > 0:
            print("\n", "\n" ,self.page, "\n", "\n" ,)
            url=self.url % (self.query,self.max_position)
            yield scrapy.Request(url,callback=self.parse_page , headers=self.headers) 


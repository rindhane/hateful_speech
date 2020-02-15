# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import sqlalchemy
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import sessionmaker
Base=declarative_base()

class TweetscraperPipeline(object):
    def __init__(self,string="../../db_files/tweet_data.db"):
        self.engine =create_engine(f'sqlite:///{string}', echo= True) 
        Base.metadata.create_all(self.engine)
    def open_spider(self,spider):
        Session=sessionmaker()
        Session.configure(bind=self.engine)
        self.session=Session()
    def process_item(self, item, spider):
        temp_cur=self.Data(tweet=item["tweet"] , tweet_type= 0, \
                            tweet_loc=item["loc"])
        self.session.add(temp_cur)
        self.session.commit()
        return item
    def close_spider(self,spider):
        self.session.close()

    class Data(Base):
            __tablename__= "tweet_data"
            id = Column(Integer, primary_key=True)
            tweet = Column(String)
            tweet_type= Column(Integer)
            tweet_loc=Column(String)
            def __repr__(self):
                return "<Data(id ={0}, tweet_type ={1}, tweet= {2})>".format( \
                    self.id,self.tweet_type, self.tweet)

#from twitter_scraper import get_tweets # instead using python-twitter client
import sqlalchemy
import pandas as pd
from sqlalchemy import create_engine
engine =create_engine('sqlite:///../db_files/tweet_data.db', echo= True)
from sqlalchemy.ext.declarative import declarative_base
Base=declarative_base()
from sqlalchemy import Column, Integer, String

class Data(Base):
    __tablename__= "tweet_data"
    id = Column(Integer, primary_key=True)
    tweet = Column(String)
    tweet_type= Column(Integer)
    tweet_loc=Column(String)
    def __repr__(self):
        return "<Data(id ={0}, tweet_type ={1}, tweet= {2})>".format( \
            self.id,self.tweet_type, self.tweet)

Base.metadata.create_all(engine)

from sqlalchemy.orm import sessionmaker
Session=sessionmaker()
Session.configure(bind=engine)
session=Session()

def populate_data():
    csv=pd.read_csv("../db_files/hate_data.csv")
    for tweet in csv[(csv["class"]==0)| (csv["class"]==1)]["tweet"]:
        temp_cur=Data(tweet =tweet , tweet_type= 1, tweet_loc="hate_data_csv")
        session.add(temp_cur)
    session.commit()

populate_data()
session.close()
print("data has been populated")

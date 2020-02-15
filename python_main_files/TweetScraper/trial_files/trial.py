import sqlalchemy
from sqlalchemy.orm import sessionmaker
Session=sessionmaker()
from sqlalchemy import create_engine
engine =create_engine('sqlite:///../../../db_files/tweet_data.db', echo= True)
Session.configure(bind=engine)
session=Session()
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

def check_tweets():
    for instance in session.query(Data).order_by(Data.id):
        print(f"tweet_type={instance.tweet_type}", 
                f"tweet is -- {instance.tweet}" )

check_tweets()
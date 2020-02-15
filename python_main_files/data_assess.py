import populate_sql as psql
import pandas
import nltk
import re
from nltk.tokenize import TweetTokenizer
from nltk.corpus import stopwords
nltk.download('stopwords')
from nltk.stem import WordNetLemmatizer
nltk.download('wordnet')




#initiation variables
cur=psql.cursor
table=psql.table_name

# ending variables initiation


#function to get query results from the table 
def query(cur,table):
    table="'"+table+"'"
    query_string = f"SELECT * FROM {table} "
    query_ans = cur.execute(query_string)
    return query_ans


#function to retrieve clean data set from thequery where the data_set has been tokenized and lemmatized 
def get_word_ls(data_frame):
    tweet_series=data_frame["tweet_text"]
    tokenizer = TweetTokenizer(strip_handles=True, reduce_len=True)
    remove_punctuations = re.compile("\W+")
    excluded_words=stopwords.words("english")
    result_set=list()
    for idx, item in enumerate(tweet_series):
        item = item.lower()
        item=remove_punctuations.sub(" ", item)
        word_token=tokenizer.tokenize(item)
        word_token=[w for w in word_token if w not in excluded_words]
        lemmed = [WordNetLemmatizer().lemmatize(w) for w in word_token]
        result_set.append(lemmed)
    return result_set
    

if __name__=="__main__":
    dem=pandas.DataFrame(data=query(cur,table).fetchall(), \
                       columns=["sr_no", "user_count", "hate_speech",\
                            "offensive", "neither", "classification" , "tweet_text"])    
    for idx , items  in enumerate(get_word_ls(dem)):
        print(items)
        if idx== 10:
            break
    
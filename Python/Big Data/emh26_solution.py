#!/usr/bin/env python
# coding: utf-8

# In[161]:


import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from pandas.tools.plotting import scatter_matrix
import os
# Use seaborn for conditional plots
import seaborn as sns

dataframe = pd.read_csv('Oscars-demographics.csv')


# In[162]:


dataframe.dtypes


# In[163]:


dataframe.shape


# In[164]:


dataframe.columns[dataframe.isnull().any()]


# In[165]:


# Get subset of dataframe.
df_subset = dataframe[['birthplace', 'date_of_birth', 'race_ethnicity', 'year_of_award', 'award']]
# Show first three rows of subset
df_subset.head(3)


# In[166]:


# Show all unique values of cloumn award in subset
df_subset['award'].unique().tolist()


# In[167]:


# Add new column equal to the length of date of birth
df_subset['ldob'] = df_subset['date_of_birth'].str.len()


# In[168]:


# Show all unique values of cloumn ldob in subset
df_subset['ldob'].unique().tolist()


# In[169]:


def clean_date_of_birth(dob):
    if(len(dob)==4):
        return '01'+'-'+'Jan'+'-'+dob
    tmp_birth = dob.split('-')
    if(len(tmp_birth[2])>4):
        return tmp_birth[0]+'-'+tmp_birth[1]+'-'+tmp_birth[2][:4]
    elif(len(tmp_birth[2])==2):
        return tmp_birth[0]+'-'+tmp_birth[1]+'-'+'19'+tmp_birth[2] 
    else:
        return dob
df_subset['date_of_birth'] = [ clean_date_of_birth(x) for x in df_subset['date_of_birth' ]]


# In[170]:


def add_country(birth_place):
    tmp_birth_place = birth_place.split(', ')
    if(birth_place=='New York City'):
        return 'New York City, USA'
    elif(len(tmp_birth_place[-1])==2):
        return birth_place + ', USA'
    else:
        return birth_place
df_subset['birthplace'] = [add_country(x) for x in df_subset['birthplace']]


# In[171]:


def get_year_of_birth(x):
    return x.split('-')[-1]
df_subset['year_of_birth'] = df_subset.apply(lambda row: get_year_of_birth(row['date_of_birth']), axis=1)


# In[172]:


df_subset['year_of_birth'] = df_subset['year_of_birth'].astype('int64')


# In[173]:


df_subset['award_age'] = df_subset['year_of_award'] - df_subset['year_of_birth']


# In[174]:


def add_country_column(birth_place):
    return birth_place.split(', ')[-1]
df_subset['country'] = [add_country_column(x) for x in df_subset['birthplace']]


# In[175]:


plt.tight_layout()

plt.rcParams["figure.figsize"] = (15, 12)
plt.rcParams["xtick.labelsize"] = 15

ax = sns.countplot(x='country',data=df_subset)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")


plt.show()

df_subset.groupby('country').size()


# In[176]:


ax = sns.countplot(x='race_ethnicity',data=df_subset)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (15, 12)
plt.rcParams["xtick.labelsize"] = 15

plt.show()

df_subset.groupby('race_ethnicity').size()


# In[177]:


def auto_boxplot(df, plot_cols, by):
    for col in plot_cols:
        fig = plt.figure(figsize=(15, 12))
        ax = fig.gca()
        df.boxplot(column = col, by = by, ax = ax)
        ax.set_title('Box plots of {} by {}'.format(col, by))
        ax.set_ylabel(col)
        plt.show()
plot_cols2 = ["award_age"]
auto_boxplot(df_subset, plot_cols2, "award")
df_subset.groupby('award').mean()


# In[178]:


df_subset['age_bucket'] = pd.cut(df_subset['award_age'], 
                                 [0, 35, 45, 55, df_subset['award_age'].max()], 
                                 labels=['0-35', '35-45', '45-55','55'+'-'+str(int(df_subset['award_age'].max()))])


# In[179]:


## Create a column with new levels for the name of the country

def add_country_category(country):
    if country=="USA":
        return "USA"
    elif country=="Italy":
        return "Italy"
    elif country=="France":
        return "France"
    elif country=="England":
        return "England"
    elif country=="Canada":
        return "Canada"
    else:
        return "Other Countries"
    
df_subset['country_category'] = df_subset.apply(lambda row: add_country_category(row['country']), axis=1)


# In[180]:


df_subset['religion'] = dataframe['religion']
df_subset['sexual_orientation'] = dataframe['sexual_orientation']


# In[181]:


ax = sns.countplot(x='religion',data=df_subset)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (15, 12)
plt.rcParams["xtick.labelsize"] = 15

plt.show()

df_subset.groupby('religion').size()


# In[182]:


best_director = df_subset[df_subset.award=='Best Director']
best_actor = df_subset[df_subset.award=='Best Actor']
best_actress = df_subset[df_subset.award=='Best Actress']
best_supporting_actor = df_subset[df_subset.award=='Best Supporting Actor']
best_supporting_actress = df_subset[df_subset.award=='Best Supporting Actress']

ax = sns.countplot(x='religion', data=best_director)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[183]:


best_director = df_subset[df_subset.award=='Best Director']
best_actor = df_subset[df_subset.award=='Best Actor']
best_actress = df_subset[df_subset.award=='Best Actress']
best_supporting_actor = df_subset[df_subset.award=='Best Supporting Actor']
best_supporting_actress = df_subset[df_subset.award=='Best Supporting Actress']

ax = sns.countplot(x='religion', data=best_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[184]:


best_director = df_subset[df_subset.award=='Best Director']
best_actor = df_subset[df_subset.award=='Best Actor']
best_actress = df_subset[df_subset.award=='Best Actress']
best_supporting_actor = df_subset[df_subset.award=='Best Supporting Actor']
best_supporting_actress = df_subset[df_subset.award=='Best Supporting Actress']

ax = sns.countplot(x='religion', data=best_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[185]:


best_director = df_subset[df_subset.award=='Best Director']
best_actor = df_subset[df_subset.award=='Best Actor']
best_actress = df_subset[df_subset.award=='Best Actress']
best_supporting_actor = df_subset[df_subset.award=='Best Supporting Actor']
best_supporting_actress = df_subset[df_subset.award=='Best Supporting Actress']

ax = sns.countplot(x='religion', data=best_supporting_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[186]:


best_director = df_subset[df_subset.award=='Best Director']
best_actor = df_subset[df_subset.award=='Best Actor']
best_actress = df_subset[df_subset.award=='Best Actress']
best_supporting_actor = df_subset[df_subset.award=='Best Supporting Actor']
best_supporting_actress = df_subset[df_subset.award=='Best Supporting Actress']

ax = sns.countplot(x='religion', data=best_supporting_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[187]:


def prepare_religion(religion):
    if(religion=='Na'):
        return None;
    else:
        return religion;
df_subset['religion'] =  df_subset.apply(lambda row: prepare_religion(row['religion']), axis=1)


# In[188]:


df_subset.religion.fillna(method='ffill', inplace=True)
df_subset.religion.fillna(method='bfill', inplace=True)


# In[189]:


ax = sns.countplot(x='religion',data=df_subset)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (15, 12)
plt.rcParams["xtick.labelsize"] = 15

plt.show()
df_subset.groupby('religion').size().sort_values(ascending=True)


# In[190]:


ax = sns.countplot(x='sexual_orientation',data=df_subset)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 15

plt.show()

df_subset.groupby('sexual_orientation').size()


# In[191]:


def fix_miss_so(orientation):
    if(orientation=="Na"):
        return "Straight"
    else:
        return orientation
df_subset['sexual_orientation'] = df_subset.apply(lambda row: fix_miss_so(row['sexual_orientation']), axis=1)


# In[192]:


def clean_rel(religion):
    if(religion=="Protestant"):
        return "Protestant"
    elif(religion=="Atheist"):
        return "Atheist"
    elif(religion=="Jewish"):
        return "Jewish"
    elif(religion=="Roman Catholic"):
        return "Roman Catholic"
    else:
        return "Other Religions"
df_subset['religion'] = df_subset.apply(lambda row: clean_rel(row['religion']), axis=1)


# In[193]:


ax = sns.countplot(x='religion',data=df_subset)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (15, 12)
plt.rcParams["xtick.labelsize"] = 15

plt.show()
df_subset.groupby('religion').size().sort_values(ascending=True)


# In[194]:


ax = sns.countplot(x='sexual_orientation',data=df_subset)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (15, 12)
plt.rcParams["xtick.labelsize"] = 15

plt.show()

df_subset.groupby('sexual_orientation').size()


# In[195]:


df_subset1 = df_subset


# In[196]:


from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split

cat_vars = ['race_ethnicity', 'age_bucket', 'country_category']
df_subset = pd.get_dummies(df_subset, columns=cat_vars)


# In[197]:


categorical = ['birthplace', 'date_of_birth', 'race_ethnicity', 
      'year_of_award', 'award', 'ldob', 
      'year_of_birth', 'award_age', 'country', 'age_bucket','country_category', 'sexual_orientation', 'religion']
def list_difference(l1, l2):
    return (list(set(l1) - set(l2)))


# In[198]:


from sklearn.feature_selection import RFE

features = df_subset.columns.tolist()
predictors = list_difference(features, categorical)
y=df_subset['award']

#train, test = train_test_split(df_subset, test_size=0.25)
predictors_train, predictors_test, y_train, y_test = train_test_split(df_subset[predictors], y, test_size=0.25)
target='award'
#creates the classifier
crf = RandomForestClassifier(n_jobs=2, n_estimators=10000, random_state=0)
rfe = RFE(crf, 5)
#train the data
crf.fit(predictors_train, y_train)


# In[199]:


# test the model
y_pred = crf.predict(predictors_test)


# In[200]:


pd.crosstab(y_test, y_pred, rownames = ['Actual'], colnames = ['Predicted'])


# In[201]:


from sklearn import metrics
print("Accuracy:",metrics.accuracy_score(y_test, y_pred))


# In[202]:


feature_imp = pd.Series(crf.feature_importances_,index=predictors).sort_values(ascending=True)
feature_imp


# In[203]:


# Clean Outlier for Best Director
best_director = df_subset1[df_subset1.award=='Best Director']

age_series = sorted(best_director['award_age'])
q1, q3= np.percentile(age_series,[25,75])
iqr = q3 - q1
lower_bound = q1 -(1.5 * iqr) 
upper_bound = q3 +(1.5 * iqr) 

def remove_age_outliers(age):
    if(age<lower_bound):
        return None
    elif(age>upper_bound):
        return None
    else:
        return age
    
best_director['award_age'] = best_director.apply(lambda row: remove_age_outliers(row['award_age']), axis=1)
best_director = best_director.dropna()


# In[204]:


# Clean Outlier for Best Actor
best_actor = df_subset1[df_subset1.award=='Best Actor']

age_series = sorted(best_actor['award_age'])
q1, q3= np.percentile(age_series,[25,75])
iqr = q3 - q1
lower_bound = q1 -(1.5 * iqr) 
upper_bound = q3 +(1.5 * iqr) 

def remove_age_outliers(age):
    if(age<lower_bound):
        return None
    elif(age>upper_bound):
        return None
    else:
        return age
    
best_actor['award_age'] = best_actor.apply(lambda row: remove_age_outliers(row['award_age']), axis=1)
best_actor = best_actor.dropna()


# In[205]:


# Clean Outlier for Best Supporting Actor
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']

age_series = sorted(best_supporting_actor['award_age'])
q1, q3= np.percentile(age_series,[25,75])
iqr = q3 - q1
lower_bound = q1 -(1.5 * iqr) 
upper_bound = q3 +(1.5 * iqr) 

def remove_age_outliers(age):
    if(age<lower_bound):
        return None
    elif(age>upper_bound):
        return None
    else:
        return age
    
best_supporting_actor['award_age'] = best_supporting_actor.apply(lambda row: remove_age_outliers(row['award_age']), axis=1)
best_supporting_actor = best_supporting_actor.dropna()


# In[206]:


# Clean Outlier for Best Actress
best_actress = df_subset1[df_subset1.award=='Best Actress']

age_series = sorted(best_actress['award_age'])
q1, q3= np.percentile(age_series,[25,75])
iqr = q3 - q1
lower_bound = q1 -(1.5 * iqr) 
upper_bound = q3 +(1.5 * iqr) 

def remove_age_outliers(age):
    if(age<lower_bound):
        return None
    elif(age>upper_bound):
        return None
    else:
        return age
    
best_actress['award_age'] = best_actress.apply(lambda row: remove_age_outliers(row['award_age']), axis=1)
best_actress = best_actress.dropna()


# In[207]:


# Clean Outlier for Best Supporting Actress
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

age_series = sorted(best_supporting_actress['award_age'])
q1, q3= np.percentile(age_series,[25,75])
iqr = q3 - q1
lower_bound = q1 -(1.5 * iqr) 
upper_bound = q3 +(1.5 * iqr) 

def remove_age_outliers(age):
    if(age<lower_bound):
        return None
    elif(age>upper_bound):
        return None
    else:
        return age
    
best_supporting_actress['award_age'] = best_supporting_actress.apply(lambda row: remove_age_outliers(row['award_age']), axis=1)
best_supporting_actress = best_supporting_actress.dropna()


# In[208]:


df_subset1 = pd.concat([best_director, best_actor, best_supporting_actor, best_actress, best_supporting_actress], ignore_index=True)


# In[209]:


df_subset1['age_bucket'] = pd.cut(df_subset1['award_age'], 
                                 [0, 35, 45, 55, df_subset1['award_age'].max()], 
                                 labels=['0-35', '35-45', '45-55','55'+'-'+str(int(df_subset1['award_age'].max()))])


# In[210]:


#Normalization Gave Me Bad Accuracy
xyz = pd.DataFrame()
xyz['award_age']=(df_subset1['award_age']-df_subset1['award_age'].min())/(df_subset1['award_age'].max()-df_subset1['award_age'].min())


# In[211]:


ax = sns.countplot(x='award_age', data=best_supporting_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (15, 12)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[212]:


ax = sns.countplot(x='award_age', data=xyz)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (15, 12)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[213]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10
ax = sns.countplot(x='sexual_orientation', data=best_director)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")


plt.show()


# In[214]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='sexual_orientation', data=best_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[215]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='sexual_orientation', data=best_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[216]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='sexual_orientation', data=best_supporting_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[217]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='sexual_orientation', data=best_supporting_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[218]:


def cleanse_orientation(orient):
    if(orient!='Straight'):
        return 'Other Orientations'
    else:
        return orient
df_subset1['sexual_orientation'] = df_subset1.apply(lambda row: cleanse_orientation(row['sexual_orientation']), axis=1)


# In[219]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='race_ethnicity', data=best_director)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[220]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='race_ethnicity', data=best_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[221]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='race_ethnicity', data=best_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[222]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='race_ethnicity', data=best_supporting_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[223]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='race_ethnicity', data=best_supporting_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[224]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='country_category', data=best_director)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[225]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='country_category', data=best_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[226]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='country_category', data=best_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[227]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']


ax = sns.countplot(x='country_category', data=best_supporting_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[228]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='country_category', data=best_supporting_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[229]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='age_bucket', data=best_director)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[230]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='age_bucket', data=best_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[231]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='age_bucket', data=best_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[232]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='age_bucket', data=best_supporting_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[233]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='age_bucket', data=best_supporting_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[234]:


def add_gender(award):
    if(award=="Best Actor" or award=="Best Supporting Actor"):
        return "Male"
    elif(award=="Best Actress" or award=="Best Supporting Actress"):
        return "Female"
    else:
        return None
df_subset1['gender'] = df_subset1.apply(lambda row: add_gender(row['award']), axis=1)


# In[235]:


ax = sns.countplot(x="gender", data=df_subset1)


# In[236]:


df_subset1.gender.fillna(method='bfill', inplace=True)


# In[237]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='gender', data=best_director)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[238]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='gender', data=best_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[239]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='gender', data=best_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[240]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='gender', data=best_supporting_actor)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[241]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']

ax = sns.countplot(x='gender', data=best_supporting_actress)
ax.set_xticklabels(ax.get_xticklabels(), rotation=40, ha="right")
plt.tight_layout()

plt.rcParams["figure.figsize"] = (10, 7)
plt.rcParams["xtick.labelsize"] = 10

plt.show()


# In[242]:


ax = sns.countplot(x="gender", data=df_subset1)


# In[243]:


df_subset1.describe()


# In[263]:


best_director = df_subset1[df_subset1.award=='Best Director']
best_actor = df_subset1[df_subset1.award=='Best Actor']
best_actress = df_subset1[df_subset1.award=='Best Actress']
best_supporting_actor = df_subset1[df_subset1.award=='Best Supporting Actor']
best_supporting_actress = df_subset1[df_subset1.award=='Best Supporting Actress']
print("Best Director Mean Age: " + str(best_director['award_age'].mean()))
print("Best Actor Mean Age: " + str(best_actor['award_age'].mean()))
print("Best Actress Mean Age: " + str(best_actress['award_age'].mean()))
print("Best Supporting Actor Mean Age: " + str(best_supporting_actor['award_age'].mean()))
print("Best Supporting Actress Mean Age: " + str(best_supporting_actress['award_age'].mean()))


# In[244]:


cat_vars1 = ['race_ethnicity', 'country_category', 'age_bucket', 'sexual_orientation','religion', 'gender'] 
df_subset1 = pd.get_dummies(df_subset1, columns=cat_vars1)


# In[245]:


features = df_subset1.columns.tolist()
categorical = ['birthplace', 'date_of_birth', 'race_ethnicity', 
                'year_of_award', 'award', 'ldob', 
                'year_of_birth','country', 'award_age','age_bucket',
                'country_category', 'sexual_orientation', 'religion', 'gender']
predictors = list_difference(features, categorical)
y=df_subset1['award']


predictors_train, predictors_test, y_train, y_test = train_test_split(df_subset1[predictors], y, test_size=0.4)
target='award'
#creates the classifier
crf = RandomForestClassifier(n_jobs=2, n_estimators=10000, random_state=0,oob_score=True)
#train the data
crf.fit(predictors_train, y_train)


# In[246]:


# test the model
y_pred = crf.predict(predictors_test)


# In[247]:


pd.crosstab(y_test, y_pred, rownames = ['Actual'], colnames = ['Predicted'])


# In[248]:


print("Accuracy:",metrics.accuracy_score(y_test, y_pred))


# In[253]:


from sklearn.metrics import classification_report
report = classification_report(y_test, y_pred)
print(report)


# In[254]:


import sklearn
sorted(sklearn.metrics.SCORERS.keys())


# In[255]:


from sklearn.model_selection import KFold, cross_val_score

crossvalidation = KFold(10,shuffle=True, random_state=0)
sr1=cross_val_score(crf, df_subset1[predictors], y, scoring='accuracy',cv=crossvalidation, n_jobs=1)


# In[256]:


sr1


# In[257]:


from sklearn.feature_selection import SelectFromModel

# Create a selector object that will use the random forest classifier to identify
# features that have an importance of more than 0.15
sfm = SelectFromModel(crf, threshold=0.05)

# Train the selector
sfm.fit(predictors_train, y_train)


# In[258]:


for feature_list_index in sfm.get_support(indices=True):
    print(predictors[feature_list_index])


# In[259]:


# Transform the data to create a new dataset containing only the most important features
# Note: We have to apply the transform to both the training X and test X data.
X_important_train = sfm.transform(predictors_train)
X_important_test = sfm.transform(predictors_test)


# In[260]:


# Create a new random forest classifier for the most important features
clf_important = RandomForestClassifier(n_estimators=10000, random_state=0, n_jobs=2)

# Train the new classifier on the new dataset containing the most important features
clf_important.fit(X_important_train, y_train)


# In[261]:


from sklearn.metrics import accuracy_score
# Apply The Full Featured Classifier To The Test Data
y_important_pred = clf_important.predict(X_important_test)

# View The Accuracy Of Our Limited Feature (2 Features) Model
accuracy_score(y_test, y_important_pred)


# In[ ]:





# In[ ]:





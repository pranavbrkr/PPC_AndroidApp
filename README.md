# PPC_AndroidApp

Android Application for "Privacy Policy Risk Assessment"

## Risk assessment of privacy policies

Standard GDPR compliance was considered to form 6 buckets for data usage, namely:
 - **Data collection**: Collected data should be bounded by organization's intent
 - **Third party collection**: Organization is obliged to inform user about third party sharing
 - **Data security**: Effective data security measures should be implemented
 - **Control of data**: User should have full control of the data
 - **Account deletion**: User has right to erase his/her data without any undue or unmentioned delay
 - **Protection of Children**: Data collection policies for underaged users if any
 
 ## Steps involved in grading the policies
 
 - Extraction of the text from the provided link
 - Stardardization of text i.e. removing html tags among other impurities to get text in pure string format
 - Classification of the sentences in the 6 aforementioned buckets
 - Grading the classified sentences into thre levels of security
 
 ## Screenshots

- Input screen

<img src="https://user-images.githubusercontent.com/31160043/193448890-3cd431df-3912-4ea2-a5aa-971fbeed0840.jpg" width="300" height="600">

- Scores screen

<img src="https://user-images.githubusercontent.com/31160043/193449025-fdfeb32f-0dd4-45b1-9695-feed93561a0c.jpg" width="300" height="600">

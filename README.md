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
 
 1. Extraction of the text from the provided link
 2. Stardardization of text i.e. removing html tags among other impurities to get text in pure string format
 3. Classification of the sentences in the 6 aforementioned buckets using logistic regression
 4. Grading the classified sentences into three levels of security using sequence modelling
 
 ## Working
 
 1. The android application provides the input for the privacy policy link
 2. This link is then sent to the [Django REST server](https://github.com/SiddharthChabukswar/ppc-rest-server)
 3. This server then
    * Scrapes the text and removes extraneous data (like HTML tags that might have been picked up during scrapping) from it
    * Categorizes each sentence into aforementioned categories
    * Grades the privacy policy based on these categorized sentences and posts the response to the application endpoint
    * The application displays the score as received from the server

## Screenshots

- Input screen

<img src="https://user-images.githubusercontent.com/31160043/193448890-3cd431df-3912-4ea2-a5aa-971fbeed0840.jpg" width="300" height="600">

- Scores screen

<img src="https://user-images.githubusercontent.com/31160043/193449025-fdfeb32f-0dd4-45b1-9695-feed93561a0c.jpg" width="300" height="600">

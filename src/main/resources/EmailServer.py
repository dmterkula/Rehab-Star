import smtplib
from email.MIMEMultipart import MIMEMultipart
from email.MIMEText import MIMEText
#from py4j.java_gateway import JavaGateway

#gateway = JavaGateway()                   # connect to the JVM
#random = gateway.jvm.java.util.Random()   # create a java.util.Random instance
#number1 = random.nextInt(10)              # call the Random.nextInt method
#number2 = random.nextInt(10)
#print(number1,number2)
#addition_app = gateway.entry_point        # get the AdditionApplication instance
#addition_app.addition(number1,number2)    # call the addition method

#To and From addresses
fromaddr = "RehabStarProject@gmail.com"
toaddr = "RehabStarProject@gmail.com"

#Making the msg object that was imported using MIME.
msg = MIMEMultipart()
msg['From'] = fromaddr
msg['To'] = toaddr

#Email Subject line
msg['Subject'] = "Forgot Password"

#Body of email.
body = "Your password is: " + "h73HDJ2f"
msg.attach(MIMEText(body, 'plain'))

#Start connection to gmail.
server = smtplib.SMTP('smtp.gmail.com', 587)
server.starttls()

#Login to gmail.
server.login(fromaddr, "X@vier19")

#Change the msg objet to something gmail can understand, i.e. a string.
text = msg.as_string()

#Send email.
server.sendmail(fromaddr, toaddr, text)

#Close connection with gmail.
server.quit()

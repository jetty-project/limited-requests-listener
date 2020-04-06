# DO NOT EDIT - See: https://www.eclipse.org/jetty/documentation/current/startup-modules.html

[description]
Enable a configured maximum number of requests per connection.

[tags]
limited-requests

[depends]
server
http

[ini]
# Configured maximum number of requests per connection
# limited.requests.max=10

[lib]
lib/limited-requests-${project.version}.jar

[xml]
etc/limited-requests.xml



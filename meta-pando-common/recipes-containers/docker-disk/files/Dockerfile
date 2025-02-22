FROM docker:27-dind
RUN apk add --update util-linux shadow e2fsprogs jq curl && rm -rf /var/cache/apk/*
#COPY entry.sh balena-api.inc /
COPY entry.sh /
RUN chmod a+x /entry.sh

VOLUME /pando-data

CMD /entry.sh

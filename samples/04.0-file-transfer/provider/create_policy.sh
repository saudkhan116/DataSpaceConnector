#!/bin/bash
# Provider
[ -z "$providerDataMgmtUrl" ] && providerDataMgmtUrl=http://localhost:8182
# APIs
[ -z "$dataMgmtPath" ] && dataMgmtPath=api/v1/data

# API Key
[ -z "$apiKey" ] && apiKey=X-Api-Key
[ -z "$apiKeyValue" ] && apiKeyValue=password

# Asset
[ -z "$defaultPolicyId" ] && defaultPolicyId="use-eu"
[ -z "$defaultPolicyDescription" ] && defaultAssetDescription='Demo Asset'
echo "Enter Policy Id (default '$defaultPolicyId')"
read -r policyId
[ -z "$policyId" ] && policyId=$defaultPolicyId

echo "Enter Policy Description (default '$defaultPolicyDescription')"
read policyDescription
[ -z "$policyDescription" ] && policyDescription=$defaultPolicyDescription

echo "Enter Asset "
read asset


__payload="
        {
        \"uid\": \"use-eu\",
        \"permissions\": [
            {
                \"edctype\": \"dataspaceconnector:permission\",
                \"uid\": null,
                \"target\": null,
                \"action\": {
                    \"type\": \"USE\",
                    \"includedIn\": null,
                    \"constraint\": null
                },
                \"assignee\": null,
                \"assigner\": null,
                \"constraints\": [],
                \"duties\": []
            }
        ],
        \"prohibitions\": [],
        \"obligations\": [],
        \"extensibleProperties\": {},
        \"inheritsFrom\": null,
        \"assigner\": null,
        \"assignee\": null,
        \"target\": \"$document\",
        \"@type\": {
            \"@policytype\": \"set\"
        }
    }"
echo ""
echo -e "\033[1mCreating Policy\033[0m"
echo "---------"
#echo $__payload | jq
echo $__payload
echo "---------"
curl -X POST "$providerDataMgmtUrl/$dataMgmtPath/policies" --header "$apiKey: $apiKeyValue" --header "Content-Type: application/json" --data "$__payload" -w 'Response Code: %{http_code}\n'
echo ""
echo -e "\033[1mCreated Policy\033[0m"
echo "---------"
# curl -X GET "$providerDataMgmtUrl/$dataMgmtPath/assets/$assetId" --header "$apiKey: $apiKeyValue" --header "Content-Type: application/json" | jq
# curl -X GET "$providerDataMgmtUrl/$dataMgmtPath/policies/$id" --header "$apiKey: $apiKeyValue" --header "Content-Type: application/json"
echo "---------"

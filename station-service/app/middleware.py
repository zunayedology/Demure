import requests
from django.conf import settings

class JWTAuthenticationMiddleware:
    def resolve(self, nxt, root, info, **args):
        request = info.context

        token = request.headers.get('Authorization', None)
        if token:
            token = token.split(' ')[1]  # Remove 'Bearer' prefix
            # Verify token with user-service
            response = requests.get(
                f"{settings.USER_SERVICE_URL}/auth/validate",
                headers={'Authorization': f'Bearer {token}'}
            )
            if response.status_code == 200:
                request.user = response.json()  # Assuming user-service returns user data
            else:
                request.user = None
        else:
            request.user = None

        return nxt(root, info, **args)

package com.eduarruiz.retosofka.security;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "LLAVE.SECRETA";
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n"
			+ "MIIEpQIBAAKCAQEAwLizF2zvxj0dsP3WiiYluPJBlFVrho0TuJo+PxlfiWZliygg\n"
			+ "1Ugn6nUg9WfxQlOpaGwvJjwu/YRLfS9Tw5U69KbB4t+R2L8cWtP5vHmq4EuQZUCw\n"
			+ "et9mWhLeBhNJi1IYntBCImfngwZsXjT5qm1VYEaCN0FHrpX2Qqfe0xxVL/mLE27w\n"
			+ "s206BlVH4aCyWYTAht0O+ippfjArhBjCgFHn1+mkf7LcFQdcwgKpUXhnPvZnKD5I\n"
			+ "sw744qX7XRhfqB6tDH2tKelOV/xXvoyDTxj/hsRHJ/RJNWxU6mi0wsU2masYEdfr\n"
			+ "PadTwiUE/NNW13LWcuqOiHJvfvK1UA12NWjTwQIDAQABAoIBAQCUV1bayCpDj9Vj\n"
			+ "3+8cRUKx+h7KW3m5hqvQY0FYdmJYM24jz859/N1klaW537kPkbALRYKBTr/oucUV\n"
			+ "ZbF3EU3g9N62j9oGWQwtSY6oKYqCvQc3OLTez0hMOsuVJ5FrYNS8oZbWPt4vt2kA\n"
			+ "low6R4/43Czs9R2E378/DVHxc5SKqJad9SRGRj0FfsB6lL5Sx+wMBq7ocYQFcE+n\n"
			+ "KyGmOUoCK4VWRO7RJEzSz+aS+jcSA3avY/eEK0N3/a9S1RXFH8U6NiDCHCP08K+H\n"
			+ "H6KUSkOjy8ZLpQHy3OJEEpTPX7p9i81fQq3hg0qV34/44oAiF1dbyQVZwRBDByM8\n"
			+ "zh6FoF61AoGBAOoq1E7XJq1FYB6rZY/h6QqvjzbR4jz0Y3G2wGgXe8Z1chbnhSre\n"
			+ "d5kN+1xshAWXB1qggNZMBEugj+jbCpp6YWo+P51b3EygDcEFHu27UXOBbRZ443vY\n"
			+ "l10lBjFwqnodCINmJ54PsaHk3dzIFAPVGzwdDa9ITGljMuRmrFfagSAvAoGBANKw\n"
			+ "oT0P3mr06jrcRK5OgtBl3NQBaBO84FBuUJwhw4J5WhDUqf/2ymY2zaX2oFJZYLJB\n"
			+ "N4HcGVRlgf37lsMnGo9cDo5uUdTOSIr7bOp5e4QiXyG/Gb8BrwSBBw9bHVchgTq2\n"
			+ "vczBOwWG+X4ZtMyIf4Of9/nf0i1oTmMY/A01Yd8PAoGBAJNKw2agEJT2yWIhP24E\n"
			+ "lLiC+1tpI5xhwNd7M4WGF/YaU9wyXuI7MxFT6SMWouh9OkP4J3IkOKN1aG/4r6/B\n"
			+ "gNr6MruBV43fMyeRWAYDSFWWOI7lFXoGIBeXvVCF5MWQ05ULQao7huklc6R3OZe7\n"
			+ "iTkDsWLq4B1+Jznhl1Bfgc9DAoGABOcRSW92GH0MekQn4Pp4tsq4AcLqnhL2EUkP\n"
			+ "3SI3/3A9dUjNj+Q5P3mk/WiowGHOCzfTR9VMTZdzF+rjzjS5vH5C5HOSueX28Cz7\n"
			+ "ktUy15dJKWrd+YDbhrpN5MCVBTMFY81ey0XvzqgeZB3LBDtk5DYzuf/IT0AvGsU5\n"
			+ "EEGevmcCgYEA1S4ywJgTODJe49jCpqu9zQ8mwE94xUTV0JucXa2TODnGjBEWnwza\n"
			+ "TVaTBm9lZy48iqAS+ccE1eqY/pUPNC+DBKbsfFk4teS7KA5xADOTRrX0+REBiLDR\n"
			+ "nf4INggIUwrO2rlhBEqu84LpG4XA1SDyScZaXp8DUjhm3XG6BTTsKtY=\n"
			+ "-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwLizF2zvxj0dsP3WiiYl\n"
			+ "uPJBlFVrho0TuJo+PxlfiWZliygg1Ugn6nUg9WfxQlOpaGwvJjwu/YRLfS9Tw5U6\n"
			+ "9KbB4t+R2L8cWtP5vHmq4EuQZUCwet9mWhLeBhNJi1IYntBCImfngwZsXjT5qm1V\n"
			+ "YEaCN0FHrpX2Qqfe0xxVL/mLE27ws206BlVH4aCyWYTAht0O+ippfjArhBjCgFHn\n"
			+ "1+mkf7LcFQdcwgKpUXhnPvZnKD5Isw744qX7XRhfqB6tDH2tKelOV/xXvoyDTxj/\n"
			+ "hsRHJ/RJNWxU6mi0wsU2masYEdfrPadTwiUE/NNW13LWcuqOiHJvfvK1UA12NWjT\n"
			+ "wQIDAQAB\n"
			+ "-----END PUBLIC KEY-----";

}

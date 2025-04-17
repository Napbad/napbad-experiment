def feistel_encrypt(plaintext, key, rounds):
    # 初始化左右两部分
    left = plaintext[:len(plaintext) // 2]
    right = plaintext[len(plaintext) // 2:]

    for i in range(rounds):
        # 保存当前右半部分
        temp = right
        # 新的右半部分 = 左半部分 ^ 轮函数(右半部分, 密钥)
        right = ''.join(chr(ord(left[j]) ^ ord(key[j % len(key)])) for j in range(len(left)))
        # 左半部分更新为原来的右半部分
        left = temp

    # 拼接左右两部分得到密文
    ciphertext = left + right
    return ciphertext


def feistel_decrypt(ciphertext, key, rounds):
    # 初始化左右两部分
    left = ciphertext[:len(ciphertext) // 2]
    right = ciphertext[len(ciphertext) // 2:]

    for i in range(rounds):
        # 保存当前右半部分
        temp = right
        # 新的右半部分 = 左半部分 ^ 轮函数(右半部分, 密钥)
        right = ''.join(chr(ord(left[j]) ^ ord(key[j % len(key)])) for j in range(len(left)))
        # 左半部分更新为原来的右半部分
        left = temp

    # 拼接左右两部分得到明文
    plaintext = left + right
    return plaintext


# 示例使用
plaintext = "CQUINFORMATIONSECURITYEXP"
key = "SECRETKEY"
rounds = 16

ciphertext = feistel_encrypt(plaintext, key, rounds)
decrypted_text = feistel_decrypt(ciphertext, key, rounds)

print(f"明文: {plaintext}")
print(f"密文: {ciphertext}")
print(f"解密后的文本: {decrypted_text}")